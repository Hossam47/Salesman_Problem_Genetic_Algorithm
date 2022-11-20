package ui;

import genetic_algorithm.Gene;
import genetic_algorithm.GeneticAlgorithm;
import model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.GeneralUtils.SCREEN_HEIGHT;
import static utils.GeneralUtils.SCREEN_WIDTH;

public class MainMap extends JDialog {
    private JButton stopButton;
    private JButton startButton;
    private JLabel bestWay;
    private JLabel numWays;
    private JLabel numGenerations;
    private JPanel mainPanel;


    private static final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(DataModel.COUNTRIES, 10000);
    ;
    private final AtomicInteger generation = new AtomicInteger(0);
    private Timer timer;

    MainMap(JFrame frame) {
        super(frame);
        setTitle("Genetic Algorithm");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setModal(true);
        setLocationRelativeTo(frame);
        setVisible(true);
        setupAlgorithm();
        initUi();
    }

    public static void main(String[] args) {
        new MainMap(null);
    }

    private void setupAlgorithm() {
    }

    private void initUi() {

        startButton.addActionListener(e -> {
            timer = new Timer(10, (ActionEvent oo) -> {
                geneticAlgorithm.runGeneticAlgorithm();
                repaint();
            });
            timer.start();
        });

        stopButton.addActionListener(e -> {
            timer.stop();
        });
    }
    @Override
    public void paintComponents(final Graphics graphics) {
        super.paintComponents(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        numGenerations.setText(String.valueOf(generation.incrementAndGet()));
        numWays.setText(geneticAlgorithm.getBestWay().toString());
        bestWay.setText(String.valueOf(geneticAlgorithm.getBestWay().getChromosomeDistance()));
        drawBestChromosome(g);
    }
    private void drawBestChromosome(final Graphics2D g) {
        final java.util.List<Gene> chromosome = geneticAlgorithm.getBestWay().getChromosome();
        g.setColor(Color.RED);
        for (int i = 0; i < chromosome.size() - 1; i++) {
            Gene gene = chromosome.get(i);
            Gene neighbor = chromosome.get(i + 1);
            System.out.println(" " + gene.getExon() + gene.getIntron() + neighbor.getExon() + neighbor.getIntron());
            g.drawLine(gene.getExon(), gene.getIntron(), neighbor.getExon(), neighbor.getIntron());
        }
        g.setColor(Color.GRAY);
        for (final Gene gene : chromosome) {
            g.fillOval(gene.getExon(), gene.getIntron(), 10, 10);
        }
    }
}
