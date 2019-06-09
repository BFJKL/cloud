<template>
    <div class="siderBar">
        <span>
            {{name}}
        </span>
    </div>
</template>

<script>
export default {
    name: 'siderBar',
    props: [
        'name'
    ]
}
</script>

<style>
.siderBar {
    font-size: 50px;
    border-bottom: 1px solid black;
}
</style>