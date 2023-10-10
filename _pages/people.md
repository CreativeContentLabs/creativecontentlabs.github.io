---
title: People
permalink: /people/
---

{% for alumni in site.data.people.alumni %}
## {{ alumni.role }}

{% for member in alumni.members %}
* ![]({{ member.image }})

  **{{ member.name }}**: {{ member.excerpt }}
{% endfor %}

{% endfor %}