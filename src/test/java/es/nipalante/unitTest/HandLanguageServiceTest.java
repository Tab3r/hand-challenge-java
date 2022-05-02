package es.nipalante.unitTest;

import es.nipalante.domain.service.CodeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HandLanguageServiceTest {

    @Test
    public void shouldReturnHello() {

        CodeParser parser = new CodeParser();

        String fileName = "test1.hpl";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            Assertions.fail("Input stream is null");
        } else {
            String code = printInputStream(inputStream);
            String output = parser.loadProgram(code);
            Assertions.assertEquals("Hello", output);
        }
    }

    @Test
    public void shouldReturnHelloWorld() {

        CodeParser parser = new CodeParser();

        String fileName = "test2.hpl";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            Assertions.fail("Input stream is null");
        } else {
            String code = printInputStream(inputStream);
            String output = parser.loadProgram(code);
            Assertions.assertEquals("Hello World!\n", output);
        }
    }

    private static String printInputStream(InputStream is) {

        StringBuilder sb = new StringBuilder();
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }
}
