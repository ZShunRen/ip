package nedui;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * The {@code DisplayImage} class provides utility methods for turning images into display photos on the chatbot UI.
 * Currently, it includes a method to apply a circular clipping filter to an {@link ImageView}.
 */
public class DisplayImage {

    /**
     * Applies a circular filter to an {@link ImageView}.
     * This method resizes the {@code ImageView} to the specified {@code size},
     * ensures the aspect ratio is preserved, and clips the image to a circular shape.
     *
     * @param existingImageView The {@link ImageView} to which the circular filter is applied.
     * @param size The diameter of the circular clip to be applied. The {@code ImageView} is resized to this size.
     */
    public static void applyCircularFilter(ImageView existingImageView, double size) {
        existingImageView.setFitWidth(size);
        existingImageView.setFitHeight(size);
        existingImageView.setPreserveRatio(true);
        existingImageView.setSmooth(true);
        Circle clip = new Circle(size / 2, size / 2, size / 2);
        existingImageView.setClip(clip);
    }
}
