package model;

import java.io.Serializable;

public enum Badge implements Serializable{
	GOOD
	//page 2
, VERYGOOD, EXCELLENT;
public Badge next() {
    Badge[] values = values();
    int nextOrdinal = (ordinal() + 1) % values.length;
    return values[nextOrdinal];
}

}
