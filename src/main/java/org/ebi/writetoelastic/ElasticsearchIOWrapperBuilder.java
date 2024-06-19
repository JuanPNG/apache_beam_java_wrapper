package org.ebi.writetoelastic;

import org.apache.beam.sdk.io.elasticsearch.ElasticsearchIO;
import org.apache.beam.sdk.transforms.ExternalTransformBuilder;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

public class ElasticsearchIOWrapperBuilder implements ExternalTransformBuilder<ElasticsearchIOWrapperConfiguration, PCollection<String>, PDone> {

    @Override
    public PTransform<PCollection<String>, PDone> buildExternal(ElasticsearchIOWrapperConfiguration configuration) {
        assert configuration != null;
        return new ElasticsearchIOWrapper(configuration.getElasticsearchUrl(), configuration.getIndex(), configuration.getEsUser(), configuration.getPassword());
    }
}