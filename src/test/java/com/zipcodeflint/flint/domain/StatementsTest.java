package com.zipcodeflint.flint.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.zipcodeflint.flint.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatementsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statements.class);
        Statements statements1 = new Statements();
        statements1.setId(1L);
        Statements statements2 = new Statements();
        statements2.setId(statements1.getId());
        assertThat(statements1).isEqualTo(statements2);
        statements2.setId(2L);
        assertThat(statements1).isNotEqualTo(statements2);
        statements1.setId(null);
        assertThat(statements1).isNotEqualTo(statements2);
    }
}
