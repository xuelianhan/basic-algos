package cn.edu.pku.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

/**
 * @author sniper
 * @date 06 Jul 2023
 */
public class PDFRemoveLines {


    public static void main(String[] args) {

    }

    public void deleteSomeLinesInPdf() {
        try {
            PdfReader reader = new PdfReader("src/main/resources/baeldung.pdf");
            PdfWriter writer = new PdfWriter("src/main/resources/baeldung-modified.pdf");
            PdfDocument pdfDocument = new PdfDocument(reader, writer);
            //todo
            pdfDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The decoding algorithm that the above string used is a shift-n algorithm.
     * This means that each character in the string is shifted by a certain number of positions.
     * In this case, the characters are shifted by 1 position to the left.
     * This is why the decoded string is "hello world".
     * The code that I provided uses a shift-n algorithm to decode the string.
     * The first part of the code, first_part = string[:len(string) // 2], splits the string into two parts.
     * The first part is then decoded by looping through each character and shifting it by 1 position to the left.
     * The second part is decoded in the same way.
     * The code then combines the two decoded parts to form the final decoded string.
     * In this case, the final decoded string is "hello world".
     * -----------------
     * def decode_string(string):
     *   first_part = string[:len(string) // 2]
     *   second_part = string[len(string) // 2:]
     *
     *   decoded_first_part = ""
     *   for char in first_part:
     *     decoded_char = chr(ord(char) - 1)
     *     decoded_first_part += decoded_char
     *
     *   decoded_second_part = ""
     *   for char in second_part:
     *     decoded_char = chr(ord(char) + 1)
     *     decoded_second_part += decoded_char
     *
     *   decoded_string = decoded_first_part + decoded_second_part
     *
     *   return decoded_string
     *
     *
     * print(decode_string("z69aMLtT=gvuTSAlqEi42DaAiEze8BLoZ0c3DQoGHUCscsPu2EIsXaK6wI2U2WkQR61vUjpPKoh5wiBBv552crxtizIRmE1ca5Uao_TGOU6Z8p0.j.gl9bvRL7PpAWq"))
     */
    public void testDecodeStr() {
        String s1 = "z69aMLtT";
        String s2 = "gvuTSAlqEi42DaAiEze8BLoZ0c3DQoGHUCscsPu2EIsXaK6wI2U2WkQR61vUjpPKoh5wiBBv552crxtizIRmE1ca5Uao_TGOU6Z8p0.j.gl9bvRL7PpAWq";
        System.out.println(s1.length());
        System.out.println(s2.length());

    }
}
