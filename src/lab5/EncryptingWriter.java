package lab5;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class EncryptingWriter extends FilterWriter {
    private final int keyCode;

    public EncryptingWriter(Writer out, char keyChar) {
        super(out);
        this.keyCode = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + keyCode);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }
}