package org.ebi.writetoelastic;

import com.google.auto.service.AutoService;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import org.apache.beam.sdk.expansion.ExternalTransformRegistrar;
import org.apache.beam.sdk.transforms.ExternalTransformBuilder;


@AutoService(ExternalTransformRegistrar.class)
public class ElasticsearchIOWrapperRegistrar implements ExternalTransformRegistrar {

    final String URN = "beam:transform:org.ebi.writetoelastic:elasticsearchiowraper:v1";

    @Override
    public Map<String, ExternalTransformBuilder<?, ?, ?>> knownBuilderInstances() {
        return ImmutableMap.of(URN, new ElasticsearchIOWrapperBuilder());
    }
}