package bsuir.passwordGenerator;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import static org.passay.IllegalRegexRule.ERROR_CODE;

@Component
public class PassayGenerator {
    public String generatePassayPassword() {
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule alphabetical = new CharacterRule(EnglishCharacterData.Alphabetical);

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(6, digits,alphabetical);
        return password;
    }
}
