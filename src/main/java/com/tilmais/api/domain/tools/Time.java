package com.tilmais.api.domain.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Time {

  private final static String KEY_SAO_PAULO = "BET";

  public static LocalDateTime getTimeNowFromSaoPaulo() {
    return LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(KEY_SAO_PAULO)));
  }
}
