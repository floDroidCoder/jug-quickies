Ext.define('UGQuickies.model.Quicky', {
    extend: 'Ext.data.Model',
    config: {
        fields: [
            { name: 'id', type: 'string' },
            { name: 'title', type: 'string'},
            { name: 'description', type: 'string'},
            { name: 'location', type: 'string'},
            { name: 'presenterName', type: 'string'},
            { name: 'usergroup', type: 'string'},
            { name: 'submissionDate', 
            	convert: function(value, record) {
            		var date = new Date(value);
            		return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
            	}
            },
        ],
    }
});