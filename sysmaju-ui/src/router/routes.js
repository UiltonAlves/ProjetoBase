function loadLayout (component) {
  return () => import(`../layouts/${component}.vue`)
}

function loadPage (component) {
  return () => import(`../pages/${component}.vue`)
}

const routes = [
  {
    path: '/',
    component: loadLayout('main'),
    children: [
      { path: '', component: loadPage('dashboard/Dashboard') },
      { path: 'dashboard', component: loadPage('dashboard/Dashboard') },
      { path: 'cliente', component: loadPage('cliente/Cliente') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: loadPage('Error404')
  })
}

export default routes
