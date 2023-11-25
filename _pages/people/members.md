---
title: Members
permalink: /people/members/
groups:
    - title: "학사과정 (B.S. Courses Students)"
      people:
        #   - name_ko: 이름
        #     name_en: Name
        #     email: foo@bar.baz
        #     affilation: >-
        #         상명대학교 ...
        #         ...
        #     interests: "..."
        #     image_url: "/assets/img/default-person-image.png"
---

> 2023년 2학기 기준

{% for group in page.groups %}
<table>
    <thead>
        <tr>
            <th colspan="3">{{ group.title }}</th>
        </tr>
    </thead>
    <tbody>
        {% for person in group.people %}
        <tr>
            <td rowspan="4"><img src="{{ person.image_url }}"/></td>
            <td style="text-align: center; font-weight: bold;">성명<br>(Name)</td>
            <td>{{ person.name_ko }} ({{ person.name_en }})</td>
        </tr>
        <tr>
            <td style="text-align: center; font-weight: bold;">소속<br>(Affiliation)</td>
            <td>{{ person.affilation }}</td>
        </tr>
        <tr>
            <td style="text-align: center; font-weight: bold;">Email</td>
            <td>
                <a href="mailto:{{ person.email }}">{{ person.email }}</a>
            </td>
        </tr>
        <tr>
            <td style="text-align: center; font-weight: bold;">관심 분야<br>(Interests)</td>
            <td>{{ person.interests }}</td>
        </tr>
        {% endfor %}
    </tbody>
</table>
{% endfor %}
