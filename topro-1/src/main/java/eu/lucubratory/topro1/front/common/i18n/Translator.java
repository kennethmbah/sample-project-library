package eu.lucubratory.topro1.front.common.i18n;

import eu.lucubratory.topro1.domain.persistence.entity.ProposalTypes;


/**
 * Collection of "reference data to screen text" functions.
 * <p/>
 * Since we are not multi-language and we have a very limited set of
 * reference data to translate, we KISS and use a global class with
 * hard coded translations.
 */
public class Translator {

    public static String translate(ProposalTypes p) {
        switch(p) {
            case CONFERENCE: return "Conference";
            case LIGHTNING_TALK: return "Lightning Talk";
            case MEET_AND_GREET: return "Meet and Greet";
            case UNCONFERENCE: return "Unconference";
            case DISCUSSION_ROUND: return "Discussion Round";
            case OTHER_BUSINESS: return "Other Business";
            case HANDS_ON_LAB: return "Hands on Lab";
            default: return "UNTRANSLATED:"+p.name();
        }
    }
}
