import genetic_algorithm.Gene;
import genetic_algorithm.GeneticAlgorithm;
import model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.GeneralUtils.*;

public class Main extends JPanel {

    private static GeneticAlgorithm geneticAlgorithm;
    AtomicInteger generation;

    Main() {
        setupUi();
        geneticAlgorithm = new GeneticAlgorithm(DataModel.COUNTRIES, ALGORITHM_LIMIT);
        this.generation = new AtomicInteger(0);
        Timer timer = new Timer(10, (ActionEvent e) -> {
            geneticAlgorithm.runGeneticAlgorithm();
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawString("Algorithm Limit =  " + geneticAlgorithm.getWays().size(), 300, 15, graphics2D);
        drawString("Generation = " + generation.incrementAndGet(), 500, 15, graphics2D);
        drawString("Best Way = " + String.format("%.2f",
                geneticAlgorithm.getBestWay().calculateChromosomeDistance()), 700, 15, graphics2D);

        drawCountries(graphics2D);
        drawBestWay(graphics2D);
    }

    public static void main(String[] args) {
        setupMainFrame(new JFrame());
    }


    private static void setupMainFrame(JFrame frame) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Genetic Algorithms");
        frame.setResizable(true);
        frame.add(new Main(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void drawCountries(Graphics2D graphics2D) {
        java.util.List<Gene> way = geneticAlgorithm.getBestWay().getChromosome();
        for (Gene gene : way) {
            drawOval(gene.getExon(), gene.getIntron(), 10, 10, graphics2D);
        }
    }
    private void drawBestWay(Graphics2D graphics2D) {
        java.util.List<Gene> way = geneticAlgorithm.getBestWay().getChromosome();
        for (int i = 0; i < way.size() - 1; i++) {
            Gene gene = way.get(i);
            Gene neighbor = way.get(i + 1);
            drawLine(gene.getExon(), gene.getIntron(), neighbor.getExon(), neighbor.getIntron(), graphics2D);
        }
    }
    private void setupUi() {
        setPreferredSize(new Dimension(SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100));
        setBackground(Color.WHITE);
    }

    private void drawString(String string, int x, int y, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(string, x, y);
    }

    private void drawLine(int x, int y, int width, int height, Graphics2D graphics2D) {
        graphics2D.setColor(Color.MAGENTA);
        graphics2D.drawLine(x, y, width, height);
    }

    private void drawOval(int x, int y, int width, int height, Graphics2D graphics2D) {
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval(x, y, width, height);
    }



}