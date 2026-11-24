package lab5;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptingReader extends FilterReader {
    private final int keyCode;

    public DecryptingReader(Reader in, char keyChar) {
        super(in);
        this.keyCode = keyChar;
    }

    @Override
    public int read() throws IOException {
        int ch = super.read();
        if (ch == -1) {
            return -1; // кінець потоку не зсуваємо
        }
        return ch - keyCode;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int charsRead = super.read(cbuf, off, len);
        if (charsRead == -1) {
            return -1;
        }
        for (int i = off; i < off + charsRead; i++) {
            cbuf[i] = (char) (cbuf[i] - keyCode);
        }
        return charsRead;
    }
}