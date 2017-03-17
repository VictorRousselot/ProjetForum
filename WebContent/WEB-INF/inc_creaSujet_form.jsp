<%@ page pageEncoding="UTF-8" %>

<label for="titreSujet">Titre du sujet <span class="requis">*</span></label>
<input type="text" id="titreSujet" name="titreSujet" value="" size="30" maxlength="60" />
<span class="erreur">${erreurs['titre']}</span>
<br />

<label for="descriptifSujet">Descriptif du sujet <span class="requis">*</span></label>
<input type="text" id="descriptifSujet" name="descriptifSujet" value="" size="30" maxlength="60" />
<span class="erreur">${erreurs['description']}</span>
<br />