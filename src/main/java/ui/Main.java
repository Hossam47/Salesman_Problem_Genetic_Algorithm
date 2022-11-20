package ui;

import genetic_algorithm.Gene;
import genetic_algorithm.GeneticAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import static model.DataModel.COUNTRIES;
import static utils.GeneralUtils.SCREEN_HEIGHT;
import static utils.GeneralUtils.SCREEN_WIDTH;

public class Main extends JPanel {
//    private JPanel mainPanel;
//    private JLabel worldMap;
//    private JButton stopButton;
//    private JButton startButton;
//    private JLabel numWays;
//    private JLabel bestWay;
//    private JLabel numGenerations;
//
    private GeneticAlgorithm geneticAlgorithm;
//
//    private Timer timer;
    private GeneticAlgorithm population;
    private AtomicInteger generation;

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    private Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        this.population = new GeneticAlgorithm(COUNTRIES, 100);
        this.generation = new AtomicInteger(0);
        final Timer timer = new Timer(10, (ActionEvent e) -> {
            this.population.runGeneticAlgorithm();
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
        g.drawString("gen : " + this.generation.incrementAndGet(), 350, 15);
        g.drawString("pop size : " + this.population.getWays().size(), 150, 15);
        g.drawString("shortest path : "
                + String.format("%.2f", this.population.getBestWay().getChromosomeDistance()), 500, 15);
        drawBestChromosome(g);
    }

    private void drawBestChromosome(final Graphics2D g) {
        final java.util.List<Gene> chromosome = this.population.getBestWay().getChromosome();
        g.setColor(Color.RED);
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
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Genetic Algorithms");
            frame.setResizable(false);
            frame.add(new Main(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


}
