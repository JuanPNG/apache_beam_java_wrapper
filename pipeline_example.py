import argparse
import apache_beam as beam
from apache_beam.transforms.external import ImplicitSchemaPayloadBuilder

# Command line arguments
parser = argparse.ArgumentParser(description='Load DToL data from string into Elasticsearch')
parser.add_argument('--address', required=True, help='Elasticsearch URL')
parser.add_argument('--index', required=True, help='Elasticsearch index name')
parser.add_argument('--username', required=True, help='Elasticsearch username')
parser.add_argument('--password', required=True, help='Elasticsearch password')

opts = parser.parse_args()

inputs = [
    'dummy_string_1',
    'dummy_string_2',
    'dummy_string_3'
]

with beam.Pipeline() as p:
    (p
     | 'ReadStrings' >> beam.Create(input)
     | 'WriteToElastic' >> beam.ExternalTransform(
                'beam:transform:org.ebi.writetoelastic:elasticsearchiowraper:v1',
                ImplicitSchemaPayloadBuilder(
                    {'address': opts.elasticsearchUrl,
                     'index': opts.index,
                     'username': opts.username,
                     'password': opts.password}),
                "localhost:12345")
     )
