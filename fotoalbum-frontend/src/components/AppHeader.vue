<template>
    <header>
      <nav id="navbar">
          <ul>
            <li v-for="link in links">
              <router-link class="link" :class="{'my-active' : $route.name === link.routeName}" :to="{ name: link.routeName}">{{ link.linkName }}</router-link>
            </li>
          </ul>
        </nav>
    </header>
</template>

<script>
export default {
    name: "Header",
    data(){
        return{
            links:[
                {
                  linkName: "Homepage",
                  routeName: "home"
                },
                {
                  linkName: 'Contattami',
                  routeName: 'contact'
                },
                {
                  linkName: 'Su di me',
                  routeName: 'about'
                }
            ]
        }
    }
}
</script>

<style lang="scss" scoped>
$nav-height: 3em;
$nav-color: #bbb;
$nav-background: #2e2e2e;


.navbar-fixed {
  position: fixed;
  top: -($nav-height);
}

nav {
  width: 100%;
  color: $nav-color;
  background: $nav-background;
  font-weight: bold;
  letter-spacing: 0.025em;
}

nav ul {
  text-align: center;
  margin: 0;
}

nav ul li {
 display: inline-block;
}

nav ul li:hover{
  background: #d84113;
}

.link{
  color: white;
  text-decoration: none;
  display: block;
  width: 100%;
  padding: 0 3em;
 line-height: 3em;
}

.my-active{
  text-shadow: 2px 2px #d84113;
}
    
</style>