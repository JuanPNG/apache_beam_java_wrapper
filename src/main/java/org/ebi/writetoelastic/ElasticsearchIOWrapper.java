/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.ebi.writetoelastic;

import org.apache.beam.sdk.io.elasticsearch.ElasticsearchIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

/**
 *
 * @author juann
 */

public class ElasticsearchIOWrapper extends PTransform<PCollection<String>, PDone> {

    private final String elasticsearchUrl;
    private final String index;
    private final String esUser;
    private final String password;

    public ElasticsearchIOWrapper(String elasticsearchUrl, String index, String esUser, String password) {
        this.elasticsearchUrl = elasticsearchUrl;
        this.index = index;
        this.esUser = esUser;
        this.password = password;

    }

    @Override
    public PDone expand(PCollection<String> input) {

        ElasticsearchIO.ConnectionConfiguration connectionConfiguration = ElasticsearchIO.ConnectionConfiguration
                .create(new String[]{elasticsearchUrl}, index, "_doc").withUsername(esUser).withPassword(password);

        input.apply(ElasticsearchIO.write().withConnectionConfiguration(connectionConfiguration));

        return PDone.in(input.getPipeline());
    }
}

// public class ElasticsearchIOWrapper extends PTransform<PCollection<String>, PDone> {
//
//    private final String elasticsearchUrl;
//    private final String index;
//    private final String esUser;
//    private final String password;
//
//    public ElasticsearchIOWrapper(String elasticsearchUrl, String index, String esUser, String password) {
//        this.elasticsearchUrl = elasticsearchUrl;
//        this.index = index;
//        this.esUser = esUser;
//        this.password = password;
//    }
//
//    class WriteToElasticDoFn extends DoFn<String, PDone> {
//
//        ElasticsearchIO.ConnectionConfiguration connectionConfiguration = ElasticsearchIO.ConnectionConfiguration
//                .create(new String[]{elasticsearchUrl}, index, "_doc").withUsername(esUser).withPassword(password);
//
////        private org.apache.beam.sdk.values.PDone PDone;
//
//        @ProcessElement
//        public void process(@Element String input, OutputReceiver<PDone> output) {
//
//            output.output(input.apply(ElasticsearchIO.write().withConnectionConfiguration(connectionConfiguration)));
////            input.apply(ElasticsearchIO.write().withConnectionConfiguration(connectionConfiguration));
////            output.output(PDone);
//        }
//
//    }
//
//    @Override
//    public PDone expand(PCollection<String> input) {
//        input.apply(
//                "WriteToElasticsearch",
//                ParDo.of(new WriteToElasticDoFn()));
//        return PDone.in(input.getPipeline());
//    }
//}