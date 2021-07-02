package com.samuraiiway.sleuthjdbc.service;

import com.samuraiiway.sleuthjdbc.constant.SqlOperation;
import com.samuraiiway.sleuthjdbc.model.SqlStatementModel;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SqlParserService {

    public SqlStatementModel analyeSqlString(String sql) {
        SqlStatementModel model = new SqlStatementModel();

        try {
            Statement stmt = CCJSqlParserUtil.parse(sql);

            if (stmt instanceof Select) {
                Select select = (Select) stmt;
                TablesNamesFinder finder = new TablesNamesFinder();

                model.setOperation(SqlOperation.SELECT);
                model.setTables(finder.getTableList(select));

            } else if (stmt instanceof Insert) {
                Insert insert = (Insert) stmt;
                TablesNamesFinder finder = new TablesNamesFinder();

                model.setOperation(SqlOperation.INSERT);
                model.setTables(finder.getTableList(insert));

            } else if (stmt instanceof Update) {
                Update update = (Update) stmt;
                TablesNamesFinder finder = new TablesNamesFinder();

                model.setOperation(SqlOperation.UPDATE);
                model.setTables(finder.getTableList(update));

            } else if (stmt instanceof Delete) {
                Delete delete = (Delete) stmt;
                TablesNamesFinder finder = new TablesNamesFinder();

                model.setOperation(SqlOperation.DELETE);
                model.setTables(finder.getTableList(delete));
            }

        } catch (Exception ex) {}

        log.info("Sql statement model: [{}]", model);
        return model;
    }
}
