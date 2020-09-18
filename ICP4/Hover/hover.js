function upDate(previewPic) {
       /*
       In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image
       */

    // change background image url of element to preview pic url on mouse over
    document.getElementById("image").style.backgroundImage="url("+previewPic.src+")"

        /*
       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
    // change text to previewpic alt
    document.getElementById("image").innerHTML=previewPic.alt
}

function unDo() {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was
   */

    // change background image url of element to none
    document.getElementById("image").style.backgroundImage="none"

    /*
   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */

    // change text back to original
    document.getElementById("image").innerHTML="Hover over an image below to display here."

}
