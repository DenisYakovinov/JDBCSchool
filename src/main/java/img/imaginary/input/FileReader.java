package img.imaginary.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import img.imaginary.exception.ReadFileException;

public class FileReader {

    public List<String> readFile(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName can't be null");
        }
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e1) {
            throw new ReadFileException("file: " + fileName + " was not found or access to the resource is denied");
        }
    }
}
