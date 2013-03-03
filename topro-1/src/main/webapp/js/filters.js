'use strict';

/* Filters */

/**
 * Filter to return the 'label' from a passed id/label array by passed id.
 *
 * Note: if you want it more complex, like accessing the scope in which the filter
 * is invoked, better switch to a directive.
 */
app.filter('pronounce', function() {
    return function(id,idLabelArray) {
        for(var i in idLabelArray) {
            if (idLabelArray[i].id===id)
                return idLabelArray[i].label;
        }
        return 'no_label_for_'+id;
    }
});


