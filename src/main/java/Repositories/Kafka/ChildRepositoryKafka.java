package Repositories.Kafka;


import Producers.KafkaChildProducer;
import Mapping.ChildMgd;
import Repositories.RepositoryInterface;
import Repositories.RepositoryDecorator;

import java.util.UUID;

public class ChildRepositoryKafka extends RepositoryDecorator<ChildMgd> {

    KafkaChildProducer kafkaChildProducer = new KafkaChildProducer();
    public ChildRepositoryKafka(RepositoryInterface<ChildMgd> childRepository) {
        super.repository = childRepository;
    }

    @Override
    public ChildMgd get(UUID id) {
        return super.repository.get(id);
    }

    @Override
    public boolean add(ChildMgd child) {
        return super.repository.add(child);
    }

    @Override
    public void update(ChildMgd child) {
        super.repository.update(child);
    }

    @Override
    public ChildMgd delete(UUID id) {
        return super.repository.delete(id);
    }
}