package com.chuf.javase;


/**
 * 文字块，所见即所得
 */
public class TextBlock {
    public static void main(String[] args) {
        String textBlock = """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>Title</title>
                        </head>
                        <body>
                            
                        </body>
                        </html>
                """;
        System.out.println("Here is the text block:\n" + textBlock);
        System.out.println("text block length:\n" + textBlock.length());
    }
}
