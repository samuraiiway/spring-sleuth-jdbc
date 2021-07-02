package com.samuraiiway.sleuthjdbc.configuration;

import brave.Tracer;
import com.samuraiiway.sleuthjdbc.model.SqlStatementModel;
import com.samuraiiway.sleuthjdbc.service.SqlParserService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyQueryExecutionListener implements QueryExecutionListener {

    @Autowired
    private Tracer tracer;

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private SqlParserService sqlParserService;

    @Override
    public void beforeQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {
    }

    @Override
    public void afterQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {
        try {
            String prefix = "datasource";
            String catalog = executionInfo.getStatement().getConnection().getCatalog();
            String name = prefix + ":" + catalog;

            for (QueryInfo queryInfo : list) {
                String sql = queryInfo.getQuery();
                log.info("Query finished in [{}] ms with statement [{}]", executionInfo.getElapsedTime(), queryInfo.getQuery());

                SqlStatementModel model = sqlParserService.analyeSqlString(sql);

                meterRegistry.timer(prefix,
                        prefix, catalog,
                        "operation", model.getOperation().name(),
                        "tables", String.join(",", model.getTables()))
                        .record(executionInfo.getElapsedTime(), TimeUnit.MILLISECONDS);

                tracer.nextSpan()
                        .name(name)
                        .tag("execution_time", "" + executionInfo.getElapsedTime())
                        .tag("operation", model.getOperation().name())
                        .tag("tables", String.join(",", model.getTables()))
                        .start((System.currentTimeMillis() - executionInfo.getElapsedTime()) * 1000)
                        .finish();

            }
        } catch (Exception ex) {
            log.error("After query data source listener exception", ex);
        }
    }
}
