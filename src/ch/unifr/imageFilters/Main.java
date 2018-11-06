package ch.unifr.imageFilters;

import com.jhlabs.image.*;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Lars Voegtlin
 * <p>
 * A CLI interface for the JH Lab filter classes.
 */
public class Main {

    static String filterName;
    static String fileFormat;
    static String[] filterArguments;
    static File outputFile;
    static BufferedImage outputImage;
    static BufferedImage inputImage;

    public static void main(String[] args) {
        Options ops = new Options();

        //chose the filter you want to use
        ops.addOption(Option.builder("f")
                .longOpt("chose-filter")
                .numberOfArgs(1)
                .desc("The filter you want to use. ") //TODO list of filters
                .type(String.class)
                .required()
                .build());

        ops.addOption(Option.builder("i")
                .longOpt("image")
                .numberOfArgs(1)
                .desc("The source image")
                .type(String.class)
                .required()
                .build());

        ops.addOption(Option.builder("o")
                .longOpt("output-path")
                .numberOfArgs(1)
                .desc("The path to the output folder")
                .type(String.class)
                .required()
                .build());

        //any number of parameters that the filter needs
        ops.addOption(Option.builder("a")
                .longOpt("arguments")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .desc("A list of arguments for the chosen filter")
                .required(false)
                .build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(ops, args);
            String inputImageName = cmd.getOptionValue('i');
            filterName = cmd.getOptionValue('f').toLowerCase();

            filterArguments = cmd.getOptionValues('a');
            if (filterArguments == null){
                filterArguments = new String[]{};
            }
            inputImage = getImageByPath(cmd.getOptionValue('i'));
            fileFormat = FilenameUtils.getExtension(inputImageName);
            outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());

            String outputPath = cmd.getOptionValue('o');
            String fileName = FilenameUtils.getName(inputImageName);
            outputFile = new File(outputPath + "/" + filterName + "_" + fileName);

            //executeClassWithArguments();
            executeFilter();
        } catch (ParseException | IOException e) {
            System.err.println("Could not parse the CLI options!");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Filter successfully applied!");
    }


    private static BufferedImage getImageByPath(String path) throws IOException {
        return ImageIO.read(new File(path));

    }

    private static void executeFilter() {
        switch (filterName) {
            case "gaussian":
                GaussianFilter gaussian = null;
                if (filterArguments.length == 1) {
                    gaussian = new GaussianFilter(Float.parseFloat(filterArguments[0]));
                } else if (filterArguments.length == 0) {
                    gaussian = new GaussianFilter();
                } else {
                    System.out.println("gaussian takes no or one argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(gaussian.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "threshold":
                ThresholdFilter threshold = null;
                if (filterArguments.length == 1) {
                    threshold = new ThresholdFilter(Integer.parseInt(filterArguments[0]));
                } else if (filterArguments.length == 0) {
                    threshold = new ThresholdFilter();
                } else {
                    System.out.println("threshold takes no or one argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(threshold.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "blur":
                BlurFilter blur = null;
                if (filterArguments.length == 0) {
                    blur = new BlurFilter();
                } else {
                    System.out.println("Blur takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(blur.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "contrast":
                ContrastFilter contrast = null;
                if (filterArguments.length == 0) {
                    contrast = new ContrastFilter();
                } else {
                    System.out.println("Contrast takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(contrast.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "exposure":
                ExposureFilter exposure = null;
                if (filterArguments.length == 0) {
                    exposure = new ExposureFilter();
                } else {
                    System.out.println("exposure takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(exposure.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "grayscale":
                GrayscaleFilter grayscale = null;
                if (filterArguments.length == 0) {
                    grayscale = new GrayscaleFilter();
                } else {
                    System.out.println("grayscale takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(grayscale.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            /*****************
             * EDGE DETECTION
             ****************/

            case "edge":
                EdgeFilter edge = null;
                if (filterArguments.length == 0) {
                    edge = new EdgeFilter();
                } else {
                    System.out.println("Edge takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(edge.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "dog":
                DoGFilter dog = null;
                if (filterArguments.length == 0) {
                    dog = new DoGFilter();
                } else {
                    System.out.println("DoG takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(dog.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "laplace":
                LaplaceFilter laplace = null;
                if (filterArguments.length == 0) {
                    laplace = new LaplaceFilter();
                } else {
                    System.out.println("LaPlace takes no argument!");
                    System.exit(1);
                }

                try {
                    ImageIO.write(laplace.filter(inputImage, outputImage), fileFormat, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
