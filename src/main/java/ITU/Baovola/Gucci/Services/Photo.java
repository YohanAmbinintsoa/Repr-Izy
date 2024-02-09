package ITU.Baovola.Gucci.Services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class Photo {
    private String base64;
    private String filename;

    public Photo(String base64, String filename) {
        this.base64 = base64;
        this.filename = filename;
    }

    public File convertToFile() throws Exception {
        Pattern pattern = Pattern.compile("data:image/(.*?);base64,");
        Matcher matcher = pattern.matcher(base64);
        matcher.find();
            // String format = matcher.group(1);
            base64 = base64.substring(matcher.end());
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
            File outputImage = new File(filename);
            ImageIO.write(ImageIO.read(inputStream), "jpg", outputImage);
            return outputImage;
    }


    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
