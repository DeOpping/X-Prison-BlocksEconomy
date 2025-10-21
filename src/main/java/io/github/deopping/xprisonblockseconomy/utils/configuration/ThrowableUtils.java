package io.github.deopping.xprisonblockseconomy.utils.configuration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class ThrowableUtils {

    private ThrowableUtils() {}

    public static String getStackTrace(final Throwable throwable) {
        final Writer writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);

        throwable.printStackTrace(printWriter);
        printWriter.flush();

        return writer.toString();
    }

}
