# SQL Comments

Java library to manage SQL statements with type safety, configurability, and multi-database support directly within Java code. See more at [SQL Comments](https://www.sqlcomments.com).

## Overview

**SQL Comments** allows you to:

- Write native SQL statements directly in Java code or separate files.
- Automatically generate type-safe POJOs for query results.
- Configure dynamic SQL statements with conditional logic.
- Support multiple database engines with ease.
- Generate CRUD operations using a Maven plugin.

By embedding SQL statements as comments, SQL Comments ensures that SQL remains readable and maintainable while still being fully type-checked against your database schema.

## Features

- **Native SQL:** Use standard SQL syntax without translating to a Java-specific DSL.
- **Type Safety:** Automatically generated POJOs match SQL query results.
- **Dynamic Queries:** Conditional SQL generation using inline JavaScript expressions.
- **Multi-DB Support:** Customize SQL statements for different databases.
- **Code Generation:** Maven plugin can generate domain classes and CRUD operations.

## Example Usage

```java
public List<CompanyInfo> findCompanies(CompanyFilter filter) {
    /*-
      @SQLComment(name="findCompanies", resultClass="example.CompanyInfo", configClass=default)
      SELECT comp.id, comp.name, country.name
      FROM companies comp
      JOIN countries country ON comp.countryId = country.id
          JOIN employees emp ON comp.id = emp.companyId  -- Add join only if //@ ceo != null
      WHERE 1=1
          AND comp.id = :companyId     -- Include only if companyId is not null
          AND comp.name LIKE :name
          AND emp.name LIKE :ceo
      ORDER BY
          country.name                   -- Only if //@ order == 'country'
          comp.name ASC, country.name DESC  -- Only if //@ order == 'company'
    */

    FindCompaniesConfig config = new FindCompaniesConfig();
    config.setCompanyId(filter.getCompanyId());

    // Configure paging
    config.limit(20);
    config.offset(0);

    // Load data
    List<CompanyInfo> companies = list(config, CompanyInfoMapper.INSTANCE);

    return companies;
}
