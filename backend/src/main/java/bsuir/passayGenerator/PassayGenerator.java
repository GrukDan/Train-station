package bsuir.passayGenerator;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

@Component
public class PassayGenerator {
    public String generatePassword() {
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule alphabetical = new CharacterRule(EnglishCharacterData.Alphabetical);

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(6, digits,alphabetical);
        return password;
    }

    public String generateAlphabetCode(int length){
        CharacterRule alphabetical = new CharacterRule(EnglishCharacterData.Alphabetical);
        PasswordGenerator codeGenerator = new PasswordGenerator();
        String code = codeGenerator.generatePassword(length,alphabetical);
        return code;
    }
}
