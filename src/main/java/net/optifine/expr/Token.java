package net.optifine.expr;

import lombok.Getter;

@Getter
public class Token {
    private TokenType type;
    private String text;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
