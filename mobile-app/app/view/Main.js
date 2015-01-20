Ext.define('UGQuickies.view.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    requires: [
        'Ext.TitleBar'
    ],
    config: {
        tabBarPosition: 'bottom',

        items: [
            {
                title: 'Quickies',
                iconCls: 'favorites',
                layout: 'vbox', //  add a layout
                scrollable: true,

                items: [
                    {
                        docked: 'top',
                        xtype: 'titlebar',
                        title: 'Quickies List'
                    },
                    {
                        xtype: 'list',
                        layout: "fit", // take as much space as available
                        flex: 1, // define flex

                        store: 'quickyStore',
                        itemTpl: '<b>{title}</b><br/><i style="color: grey">{submissionDate}</i><br/><small>{location}</small>',

                        listeners: {
                            select: function(view, record) {
                                //Ext.Msg.alert('Selected!', 'You selected ' + record.get('description'));
                                Ext.Msg.show({
                                    title : record.get('title'),
                                    html : '<div style="color: white">'+
                                        'On : <small>'+record.get('submissionDate')+'</small><br/>'+
                                        'At : <small>'+record.get('location')+'</small><br/>'+
                                        'Desc : <small>' + record.get('description') + '</small><br/>' +
                                        'By : <small>' + record.get('presenterName') + '</small><br/>' +
                                        'Group : <small>' + record.get('usergroup') + '</small><br/>' +
                                        '</div>',
                                    buttons : Ext.Msg.OK,
                                    minWidth : 300
                                });
                            }
                        }
                    }
                ]
            },
            {
                title: 'About',
                iconCls: 'user',

                items: [
                    {
                        docked: 'top',
                        xtype: 'titlebar',
                        title: 'About us'
                    }
                ],
                html: [
                        "We are 2 passionated developpers !<br/>",
                        "Florian Genaudet <a href=\"https://twitter.com/FGenaudet\">@FGenaudet</a> - Looking for a job <br/>",
                        "Julien Roux <a href=\"https://twitter.com/roujul\">@roujul</a> - Nethings, UK <br/>",
                        "<br/>",
                        "This app is made for the UG Challenge : <a href=\"http://genevajug.ch/challenge\">More details</a>"
                ].join("")
            }
        ]
    }
});
