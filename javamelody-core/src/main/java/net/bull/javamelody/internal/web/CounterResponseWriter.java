/*
 * Copyright 2008-2026 by Emeric Vernat
 *
 *     This file is part of Java Melody.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bull.javamelody.internal.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Implémentation de PrintWriter qui fonctionne avec le {@link CounterServletResponseWrapper}.
 *
 * @author Emeric Vernat
 */
public class CounterResponseWriter extends PrintWriter {
    private static final int LINE_SEPARATOR_LENGTH = System.lineSeparator().length();

    private final PrintWriter writer;
    private long dataLength;

    /**
     * Construit un PrintWriter associé avec la réponse spécifiée.
     *
     * @param response HttpServletResponse
     * @throws IOException Erreur d'entrée/sortie
     */
    CounterResponseWriter(HttpServletResponse response) throws IOException {
        this(response.getWriter());
    }

    /**
     * Construit un servlet output stream associé avec l'output stream spécifiée.
     *
     * @param writer Writer
     */
    public CounterResponseWriter(PrintWriter writer) {
        super(writer);
        this.writer = writer;
    }

    /**
     * Retourne la valeur de la propriété dataLength.
     *
     * @return long
     */
    public long getDataLength() {
        return dataLength;
    }

    /**
     * Réinitialiser dataLength à 0.
     */
    public void reset() {
        dataLength = 0;
    }

    @Override
    public void write(String s) {
        writer.write(s);
        dataLength += s.length();
    }

    @Override
    public void write(String s, int off, int len) {
        writer.write(s, off, len);
        dataLength += len;
    }

    @Override
    public void write(char[] buf) {
        writer.write(buf);
        dataLength += buf.length;
    }

    @Override
    public void write(char[] buf, int off, int len) {
        writer.write(buf, off, len);
        dataLength += len;
    }

    @Override
    public void write(int c) {
        writer.write(c);
        dataLength++;
    }

    @Override
    public void println() {
        writer.println();
        dataLength += LINE_SEPARATOR_LENGTH;
    }
}
