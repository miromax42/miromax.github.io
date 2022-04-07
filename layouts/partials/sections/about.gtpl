{{ $sectionID := replace (lower .section.name) " " "-"  }}
{{ if .section.id }}
  {{ $sectionID = .section.id }}
{{ end }}

{{ $author:= site.Data.author }}
{{ if (index site.Data site.Language.Lang).author }}
  {{ $author = (index site.Data site.Language.Lang).author }}
{{ end }}
<div class="container anchor p-lg-5 about-section" id="{{ $sectionID }}">
  <div class="row pt-sm-2 pt-md-4 align-self-center">
    <!-- summary -->
    <!-- takes up full section width if no badges/soft skills are specified -->
    {{ if or (.softSkills) (.badges) }} <div class="col-sm-6"> {{ else }} <div class="col-sm-12"> {{ end }}
      <h3 class="p-1">{{ $author.name }}</h3>
      {{ if .designation }}
      <h5 class="p-1">
        {{ .designation }}
        {{ if .company }}
        {{ i18n "at"}} <a href="{{ .company.url }}" title="{{ .company.name }}" target="_blank" rel="noopener">{{ .company.name }}</a>
        {{ end }}
      </h5>
      {{ end }}
      <p class="p-1 text-justify">
        {{ .summary | markdownify | emojify }}
      </p>
      <div class="text-container">
        <ul class="social-link d-flex justify-content-center">
          {{ range .socialLinks }}
          <li>
            {{ if eq .name "Email" }}
              <a href="mailto:{{ .url }}" title="{{ .name }}" target="_blank" rel="noopener"><i class="{{ .icon }}"></i></a>
            {{ else if eq .name (i18n "phone") }}
              <a href="tel:{{ .url }}" title="{{ .name }}" target="_blank" rel="noopener"><i class="{{ .icon }}"></i></a>
            {{ else }}
              <a href="{{ .url }}" title="{{ .name }}" target="_blank" rel="noopener"><i class="{{ .icon }}"></i></a>
            {{ end }}
          </li>
          {{ end }}
        </ul>
      </div>
      {{ if .resume }}
      <a href="{{ .resume | relURL }}" title="{{ i18n "resume"}}" target="#"
        ><button class="btn btn-dark">{{ i18n "resume"}}</button></a
      >
      {{ end }}
    </div>
    <!-- soft skills circular-progressbar -->
    <div class="col-sm-6 pt-5 pl-md-4 pl-sm-3 pt-sm-0">
      <div class="row">
        {{ range .badges }}
          {{ partial "misc/badge.html" . }}
        {{ end }}
        <!-- TODO: Remove this part in version v4+ -->
        <!-- Keep backward compatibility with old configuration -->
        {{ range .softSkills }}
          {{ partial "misc/soft-skills.html" . }}
        {{ end }}
      </div>
    </div>
  </div>
</div>
{{ if .Page.Store.Get "hasMermaid" }}
  <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
  <script>
    mermaid.initialize({ startOnLoad: true });
  </script>
{{ end }}
