import genetic_algorithm.Gene;
import genetic_algorithm.GeneticAlgorithm;
import model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.GeneralUtils.SCREEN_HEIGHT;
import static utils.GeneralUtils.SCREEN_WIDTH;

public class Main extends JPanel {

    private static GeneticAlgorithm geneticAlgorithm;
    AtomicInteger generation;

    Main() {
        setPreferredSize(new Dimension(SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100));
        setBackground(Color.WHITE);
        geneticAlgorithm = new GeneticAlgorithm(DataModel.COUNTRIES, 100000);
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
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("Generation = " + generation.incrementAndGet(), 300, 15);
        g.drawString("Algorithm Limit =  " + geneticAlgorithm.getWays().size(), 450, 15);
        g.drawString("Best Way = " + String.format("%.2f", geneticAlgorithm.getBestWay().getChromosomeDistance()), 600, 15);
        drawBestChromosome(g);

    }

    private void drawBestChromosome(final Graphics2D g) {
        final java.util.List<Gene> chromosome = this.geneticAlgorithm.getBestWay().getChromosome();
        g.setColor(Color.MAGENTA);
        for (int i = 0; i < chromosome.size() - 1; i++) {
            Gene gene = chromosome.get(i);
            Gene neighbor = chromosome.get(i + 1);
            g.drawLine(gene.getExon(), gene.getIntron(), neighbor.getExon(), neighbor.getIntron());
        }
        g.setColor(Color.GRAY);
        for (final Gene gene : chromosome) {
            g.fillOval(gene.getExon(), gene.getIntron(), 10, 10);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Genetic Algorithms");
            frame.setResizable(true);
            frame.add(new Main(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}