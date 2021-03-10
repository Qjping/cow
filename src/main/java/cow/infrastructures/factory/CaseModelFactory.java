package cow.infrastructures.factory;

import cow.infrastructures.model.CaseModel;
import org.springframework.stereotype.Component;


import javax.inject.Provider;

@Component
public class CaseModelFactory {
    private final Provider<CaseModel> provider;

    public CaseModelFactory(Provider<CaseModel> provider) {
        this.provider = provider;
    }

    public CaseModel create() {
        return provider.get();
    }
}
