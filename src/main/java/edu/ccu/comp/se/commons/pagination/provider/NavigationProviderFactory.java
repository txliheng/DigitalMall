package edu.ccu.comp.se.commons.pagination.provider;


import java.util.HashMap;
import java.util.Map;

import edu.ccu.comp.se.commons.pagination.INavigationProvider;

public class NavigationProviderFactory {
    
    private static Map<String, INavigationProvider> map = new HashMap<String, INavigationProvider>();
    
    static {
        map.put("v1", new NavigationProviderV1());
        map.put("v2", new NavigationProviderV2());
    }
    
    public static INavigationProvider getNavigationProvider(String version) {
        INavigationProvider provider = map.get(version);
        if(provider == null) {
            provider = map.get("v1");
        }
        return provider;
    }
}
