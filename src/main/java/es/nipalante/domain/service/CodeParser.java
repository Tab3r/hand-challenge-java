package es.nipalante.domain.service;

import com.vdurmont.emoji.EmojiParser;
import es.nipalante.domain.entities.Loops;
import es.nipalante.domain.entities.Memory;

import java.util.List;

public class CodeParser {
    private Memory memory = new Memory();
    private Loops loopsManager;
    private List<String> decodedCode;
    private Integer codePosition = 0;
    private StringBuilder output = new StringBuilder();

    public CodeParser() {
    }

    public String loadProgram(String hplCode) {
        this.output.setLength(0);
        this.decodedCode = EmojiParser.extractEmojis(hplCode);

        loopsManager = new Loops(this.decodedCode);

        while (codePosition < this.decodedCode.size()) {
            String emoji = this.decodedCode.get(codePosition);
            this.actions(emoji);
        }

        return output.toString();
    }

    private void actions(String action) {
        switch (action) {
            case "\uD83D\uDC47":
                this.memory.getCurrentCell().decreaseValue();
                codePosition++;
                break;
            case "\uD83D\uDC46":
                this.memory.getCurrentCell().increaseValue();
                codePosition++;
                break;
            case "\uD83D\uDC49":
                this.memory.moveRight();
                codePosition++;
                break;
            case "\uD83D\uDC48":
                this.memory.moveLeft();
                codePosition++;
                break;
            case "\uD83E\uDD1C":
                if (this.memory.getCurrentCell().getValue() == 0) {
                    Integer jumpPosition = this.loopsManager.getEndPosition(codePosition);
                    codePosition = jumpPosition + 1;
                } else codePosition++;
                break;
            case "\uD83E\uDD1B":
                if (this.memory.getCurrentCell().getValue() != 0) {
                    Integer jumpPosition = this.loopsManager.getStarPosition(codePosition);
                    codePosition = jumpPosition + 1;
                } else codePosition++;
                break;
            case "\uD83D\uDC4A":
                char charOutput = (char) this.memory.getCurrentCell().getValue().intValue();
                System.out.print(charOutput);
                System.out.flush();
                codePosition++;
                output.append(charOutput);
                break;
        }
    }
}
