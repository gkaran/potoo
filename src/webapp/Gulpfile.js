const gulp = require('gulp');
const del = require('del');
const typescript = require('gulp-typescript');
const tscConfig = require('./tsconfig.json');
const browserSync = require('browser-sync');
const proxy = require('proxy-middleware');
const url = require('url');
const modRewrite = require('connect-modrewrite');
var runSequence = require('run-sequence');
const outputDir = 'dist';

gulp.task('clean', function () {
    return del(outputDir + '/**');
});

gulp.task('compile', function () {
    return gulp
        .src('app/**/*.ts')
        .pipe(typescript(tscConfig.compilerOptions))
        .pipe(gulp.dest(outputDir  + '/app'));
});

gulp.task('copy:libs', function() {
    return gulp.src([
            'node_modules/es6-shim/es6-shim.min.js',
            'node_modules/systemjs/dist/system-polyfills.js',
            'node_modules/angular2/bundles/angular2-polyfills.js',
            'node_modules/systemjs/dist/system.src.js',
            'node_modules/rxjs/bundles/Rx.js',
            'node_modules/angular2/bundles/angular2.dev.js',
            'node_modules/angular2/bundles/http.dev.js',
            'node_modules/angular2/bundles/router.dev.js',
            'node_modules/ng2-bootstrap/bundles/ng2-bootstrap.min.js'
        ])
        .pipe(gulp.dest(outputDir  + '/lib'))
});

gulp.task('copy:assets', function() {
    return gulp.src(['app/**/*', 'index.html', 'styles.css', '!app/**/*.ts'], { base : './' })
        .pipe(gulp.dest(outputDir));
});

gulp.task('build', function() {
    runSequence('clean', ['compile', 'copy:libs', 'copy:assets']);
});

gulp.task('default', ['build']);

gulp.task('watch:init', function() {
    runSequence('build', 'watch');
});

gulp.task('watch', function () {
    gulp.watch(['app/**/*', 'index.html', 'styles.css', '!app/**/*.ts'], ['copy:assets']);
    gulp.watch('app/**/*.ts', ['compile']);
});

gulp.task('browser-sync', ['watch:init'], function () {

    var apiProxyOptions = url.parse('http://localhost:8080/api');
    apiProxyOptions.route = '/api';

    browserSync.init('dist/**', {
        watchTask: true,
        server: {
            baseDir: "dist",
            middleware: [
                proxy(apiProxyOptions),
                modRewrite(['!\\.\\w+$ /index.html [L]'])
            ]
        }
    });
});
