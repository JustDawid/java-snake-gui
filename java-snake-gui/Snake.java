import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake extends JPanel implements ActionListener, KeyListener {
    // Zmienne stanu gry
    private final int szerokosc = 20;
    private final int wysokosc = 20;
    private final int kratka = 20;

    private boolean koniec;
    private int x, y, owocX, owocY, wynik, lOgonow;

    private int[] ogonX = new int[szerokosc * wysokosc];
    private int[] ogonY = new int[szerokosc * wysokosc];

    enum Kierunek {STOP, LEWO, PRAWO, GORA, DOL}
    private Kierunek kier;

    private Timer czas;
    private Random rand = new Random();

    // Inicjalizacja
    public Snake() {
        this.setPreferredSize(new Dimension(szerokosc * kratka, wysokosc * kratka));
        this.setBackground(new Color(0, 55, 0));
        this.setFocusable(true);
        this.addKeyListener(this);

        ustawienie();
        czas = new Timer(100, this);
        czas.start();
    }
    
    private void ustawienie() {
        koniec = false;
        kier = Kierunek.STOP;
        x = szerokosc / 2;
        y = wysokosc / 2;
        owocX = rand.nextInt(szerokosc);
        owocY = rand.nextInt(wysokosc);
        wynik = 0;
        lOgonow = 0;
    }

    // Logika
    private void logik() {
        if (koniec || kier == Kierunek.STOP) return;

        int popX = ogonX[0];
        int popY = ogonY[0];
        int pop2X, pop2Y;

        ogonX[0] = x;
        ogonY[0] = y;
        
        for (int i = 1; i < lOgonow; i++) {
            pop2X = ogonX[i];
            pop2Y = ogonY[i];
            ogonX[i] = popX;
            ogonY[i] = popY;
            popX = pop2X;
            popY = pop2Y;
        }
        
        // Glowa
        switch (kier) {
            case LEWO:
                x--;
                break;
            case PRAWO:
                x++;
                break;
            case GORA:
                y--;
                break;
            case DOL:
                y++;
                break;        
            default:
                break;
        }
        
        // Kolizja sciany
        if (x >= szerokosc || x < 0 || y >= wysokosc || y < 0) {
            koniec = true;
        }

        // Owoce
        if (x == owocX && y == owocY) {
            wynik += 10;
            owocX = rand.nextInt(szerokosc);
            owocY = rand.nextInt(wysokosc);
            lOgonow++;
        }

        // Kolizja z ogonem
        for (int i = 0; i < lOgonow; i++) {
            if (ogonX[i] == x && ogonY[i] == y) {
                koniec = true;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!koniec) {
            g.setColor(new Color(170, 0, 0));
            g.fillRect(owocX * kratka, owocY * kratka, kratka, kratka);

            g.setColor(Color.BLACK);
            g.fillRect(x * kratka, y * kratka, kratka, kratka);

            for (int i = 0; i < lOgonow; i++) {
                if (i%2!=0){
                    g.setColor(new Color(0, 0, 120));
                    g.fillRect(ogonX[i] * kratka, ogonY[i] * kratka, kratka, kratka);
                } else{
                    g.setColor(new Color(0, 0, 200));
                    g.fillRect(ogonX[i] * kratka, ogonY[i] * kratka, kratka, kratka);
                }
            }

            g.setColor(Color.WHITE);
            g.drawString("Wynik: " + wynik, 10, 20);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Koniec gry twój wynik to: " + wynik , (szerokosc * kratka) / 2 - 60, (wysokosc * kratka) / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        int klik = k.getKeyCode();
        if (klik == KeyEvent.VK_A && kier != Kierunek.PRAWO) kier = Kierunek.LEWO;
        if (klik == KeyEvent.VK_D && kier != Kierunek.LEWO) kier = Kierunek.PRAWO;
        if (klik == KeyEvent.VK_W && kier != Kierunek.DOL) kier = Kierunek.GORA;
        if (klik == KeyEvent.VK_S && kier != Kierunek.GORA) kier = Kierunek.DOL;

        if (klik == KeyEvent.VK_X) koniec = true;
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}


    @Override
    public void actionPerformed(ActionEvent e) {
        logik();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake gra");
        Snake gra = new Snake();
        frame.add(gra);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}