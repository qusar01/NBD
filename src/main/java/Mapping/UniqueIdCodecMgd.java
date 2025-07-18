package Mapping;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.UUID;

public class UniqueIdCodecMgd implements Codec<UniqueIdMgd> {

    private Codec<UUID> uuidCodec;
    public UniqueIdCodecMgd(CodecRegistry registry) {
        uuidCodec = registry.get(UUID.class);
    }
    @Override
    public UniqueIdMgd decode(BsonReader bsonReader, DecoderContext decoderContext) {
        UUID uuid = uuidCodec.decode(bsonReader, decoderContext);
        return new UniqueIdMgd(uuid);
    }
    @Override
    public void encode(BsonWriter bsonWriter, UniqueIdMgd uuid, EncoderContext encoderContext) {
        uuidCodec.encode(bsonWriter, uuid.getUUID(), encoderContext);
    }
    @Override
    public Class<UniqueIdMgd> getEncoderClass() {
        return UniqueIdMgd.class;
    }
}