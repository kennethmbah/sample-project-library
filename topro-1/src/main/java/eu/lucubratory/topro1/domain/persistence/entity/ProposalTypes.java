package eu.lucubratory.topro1.domain.persistence.entity;

import java.io.Serializable;

/**
 * Enumerates topic proposal types.
 */
public enum ProposalTypes implements Serializable {

    CONFERENCE,
    LIGHTNING_TALK,
    MEET_AND_GREET,
    UNCONFERENCE,
    HANDS_ON_LAB,
    DISCUSSION_ROUND,
    OTHER_BUSINESS;
}
