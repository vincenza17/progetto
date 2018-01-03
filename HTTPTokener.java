package org.json;

import prova.JSONException;
import prova.JSONTokener;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
 * The HTTPTokener extends the JSONTokener to provide additional methods
 * for the parsing of HTTP headers.
 * @author JSON.org
 * @version 2008-09-18
 */
public class HTTPTokener extends JSONTokener {

    /**
     * Construct an HTTPTokener from a string.
     * @param s A source string.
     */
    public HTTPTokener(String s) {
        super(s);
    }


    /**
     * Get the next token or string. This is used in parsing HTTP headers.
     * @throws JSONException
     * @return A String.
     */
    public static String forMethod(char c, char q, StringBuffer sb ) {//mio
    	 while(c < ' ') {
             c = next();
             if (c < ' ') {
                 throw syntaxError("Unterminated string.");
             }
             if (c == q) {
                 return sb.toString();
             }
             sb.append(c);
         }
    }
    public static String ifMethod(char c, char q, StringBuffer sb ) {//mio
    	if (c == '"' || c == '\'') {
            q = c;
            forMethod(c,q,sb);
           
        } 
    }
    public String nextToken() throws JSONException {
        char c=0;
        char q=0;
        StringBuffer sb = new StringBuffer();
        boolean i=Character.isWhitespace(c);//mio
        do {
            c = next();
           
        } while (i);
        
        
        ifMethod(c, q, sb);
        
        while(c == 0) {
            if (c == 0 || Character.isWhitespace(c)) {
                return sb.toString();
            }
            sb.append(c);
            c = next();
        }
    }
}