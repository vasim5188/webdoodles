package com.webdoodles.tutorials.core.models;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Filter {
    @ValueMapValue
    private String[] tags;

    @ScriptVariable
    private ResourceResolver resolver;

    private Map<String, String> tagDetailsMap;

    @PostConstruct
    protected void init() {
        tagDetailsMap = new LinkedHashMap<>();
        TagManager tagManager = resolver.adaptTo(TagManager.class);
        for (String tagString : tags) {
            Tag tag = tagManager.resolve(tagString);
            tagDetailsMap.put(tag.getTitle(), tagString);
        }
    }

    public Map<String, String> getTagDetailsMap() {
        return tagDetailsMap;
    }
}
