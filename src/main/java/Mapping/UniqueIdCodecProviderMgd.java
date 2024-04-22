package Mapping;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class UniqueIdCodecProviderMgd implements CodecProvider {
    public UniqueIdCodecProviderMgd() {
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry codecRegistry) {
        if (clazz == UniqueIdMgd.class) {
            return (Codec<T>) new UniqueIdCodecMgd(codecRegistry);
        }
        return null;
    }
}
