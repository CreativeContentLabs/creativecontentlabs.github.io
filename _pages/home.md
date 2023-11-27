---
title: Creative Content Labs
layout: splash
permalink: /
header:
    overlay_color: "#000"
    overlay_filter: "0.5"
    overlay_image: https://picsum.photos/1080/200
excerpt: >
    since March 01, 2009
---

# Researches
---

<div class="feature__wrapper">

  {% for research in site.data.researches.main %}
    <div class="feature__item{% if include.type %}--{{ include.type }}{% endif %}">
      <div class="archive__item">
        {% if research.image_path %}
          <div class="archive__item-teaser">
            <img src="{{ research.image_path | relative_url }}"
                 alt="{% if research.alt %}{{ research.alt }}{% endif %}">
            {% if research.image_caption %}
              <span class="archive__item-caption">{{ research.image_caption | markdownify | remove: "<p>" | remove: "</p>" }}</span>
            {% endif %}
          </div>
        {% endif %}

        <div class="archive__item-body">
          {% if research.title %}
            <h2 class="archive__item-title">{{ research.title }}</h2>
          {% endif %}

          {% if research.excerpt %}
            <div class="archive__item-excerpt">
              {{ research.excerpt | markdownify }}
            </div>
          {% endif %}

          {% if research.url %}
            <p><a href="{{ research.url | relative_url }}" class="btn {{ research.btn_class }}">{{ research.btn_label | default: site.data.ui-text[site.locale].more_label | default: "Learn More" }}</a></p>
          {% endif %}
        </div>
      </div>
    </div>
  {% endfor %}

</div>
