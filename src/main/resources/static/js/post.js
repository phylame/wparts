"use strict";

$(function () {
    new Vue({
        el: "#quick-posts",
        data: {
            activated: "",
            posts: []
        },
        methods: {
            loadPosts: function (key) {
                if (key !== this.activated) {
                    this.posts = [];
                    this.activated = key;
                    var n = Math.trunc(Math.random() * 8 + 1);
                    for (var i = 0; i < n; ++i) {
                        this.posts.push({
                            id: Math.trunc(Math.random() * 100 + 1),
                            title: "This is post with order " + i + " for " + key
                        })
                    }
                }
            }
        },
        mounted: function () {
            this.loadPosts("popular")
        }
    });
    new Vue({
        el: "#category-list",
        data: {
            count: 0,
            items: [],
            loading: false
        },
        methods: {
            loadMore: function () {
                this.loading = true;
                var currentCount = this.items.length;
                for (var i = 0, end = this.count - currentCount; i < end; ++i) {
                    this.items.push({
                        id: i + 1 + currentCount,
                        name: "category " + (i + 1 + currentCount),
                        posts: Math.trunc(Math.random() * 5 + 1)
                    })
                }
                this.loading = false
            }
        },
        mounted: function () {
            this.count = 6;
            this.loadMore()
        }
    });
    new Vue({
        el: "#archive-list",
        data: {
            count: 0,
            items: [],
            loading: false
        },
        methods: {
            loadMore: function (size) {

            }
        },
        mounted: function () {

        }
    })
});
