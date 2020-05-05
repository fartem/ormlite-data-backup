package com.smlnskgmail.jaman.ormlitedatabackup.entities.entities;

import org.junit.Test;

public abstract class BaseEntityTest {

    @Test
    public abstract void validateFields();

    @Test
    public abstract void validateEquals();

    @Test
    public abstract void validateHashCode();

}
