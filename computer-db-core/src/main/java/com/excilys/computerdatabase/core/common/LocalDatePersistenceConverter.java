package com.excilys.computerdatabase.core.common;

import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Utilisty class for converting LocalDate to SQL Date
 * @author excilys
 *
 */
@Converter
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, java.sql.Date> {

  /* (non-Javadoc)
 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
 */
@Override
  public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
    if (entityValue != null) {
      return java.sql.Date.valueOf(entityValue);
    }
    return null;
  }

  /* (non-Javadoc)
 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
 */
@Override
  public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
    if (databaseValue != null) {
      return databaseValue.toLocalDate();
    }
    return null;
  }
}